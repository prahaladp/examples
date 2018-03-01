# include "../common/header.h"

typedef struct json_s {
	char	token1[80];
	char	token2[80];
	struct json_s	*next;
} json_t;

typedef struct output_s {
	json_t		*js;
	json_t		*curr;
} output_t;

typedef struct token_s {
	char		*str;
	int		indx;
	int		len;
} token_t;

int elem_parser(token_t *, output_t *);

void
print_tokens(output_t *o)
{
	json_t	*curr;

	printf("printing tokens\n");
	curr = o->js;
	while (curr) {
		printf("%s -> %s\n", curr->token1, curr->token2);
		curr = curr->next;
	}
}

void
strip_spaces(token_t *tok)
{
	int	indx = tok->indx;

	while (indx < tok->len && tok->str[indx] == ' ') {
		indx++;
	}

	tok->indx = indx;
}

int
end_parser(token_t *tok, output_t *o)
{
	strip_spaces(tok);

	if (tok->str[tok->indx] == '}') {
		return (0);
	}

	return (-1);
}

void
extract(char *dst, char *src, int st, int end)
{
	int	indx = 0;

	while (st <= end) {
		dst[indx++] = src[st++];
	}
	dst[indx] = '\0';
}

int
sep_parser(token_t *tok, output_t *o)
{
	strip_spaces(tok);

	if (tok->indx >= tok->len) {
		printf("sep : unable to find separator\n");
		return (-1);
	}

	if (tok->str[tok->indx] != ',') {
		printf("sep : incorrect token %c\n", tok->str[tok->indx]);
		return (-1);
	}

	tok->indx++;
	return (elem_parser(tok, o));
}


int
val_parser(token_t *tok, output_t *o)
{
	int	start, end;
	int	indx;
	json_t	*js;

	printf("val_parser : token recvd = %c at %d %d\n", tok->str[tok->indx], tok->indx, tok->len);
	strip_spaces(tok);
	printf("val_parser : token recvd = %c at %d\n", tok->str[tok->indx], tok->indx);

	if (tok->str[tok->indx] == '"') {
		indx = tok->indx + 1;
		while (indx < tok->len) {
			printf("val_parser : token recvd = %c\n", tok->str[indx]);
			if (tok->str[indx] == '"') {
				break;
			}
			indx++;
		}

		if (indx >= tok->len) {
			printf("val_parser : incomplete token stream\n");
			return (-1);
		}

		js = o->curr;
		extract(js->token2, tok->str, tok->indx + 1, indx - 1);
		tok->indx = indx + 1;

		/* two possibilities */
		if (end_parser(tok, o) == 0) {
			printf("val_parser : ending\n");
			return  (0);
		}

		if (sep_parser(tok, o) == 0) {
			printf("separator parser succeeded\n");
			return (0);
		}

		if (elem_parser(tok, o) == 0) {
			printf("val_parser : another element found\n");
			return (0);
		}
	}

	printf("val_parser : error\n");
	return (-1);
}

int
val_sep_parser(token_t *tok, output_t *o)
{
	strip_spaces(tok);
	if (tok->indx >= tok->len) {
		printf("val_sep : unable to find separator\n");
		return (-1);
	}

	if (tok->str[tok->indx] != ':') {
		printf("val_sep : incorrect token %c\n", tok->str[tok->indx]);
		return (-1);
	}

	tok->indx++;
	return (val_parser(tok, o));
}

int
elem_parser(token_t *tok, output_t *o)
{
	int	start, end;
	json_t	*js;
	int	indx;

	printf("elem_parser : token recvd = %c at index %d\n", tok->str[tok->indx], tok->indx);

	strip_spaces(tok);
	indx = tok->indx;
	if (tok->str[tok->indx] == '"') {
		indx = tok->indx + 1;
		while (indx < tok->len) {
			if (tok->str[indx] == '"') {
				break;
			}
			indx++;
		}

		if (indx >= tok->len) {
			printf("elem_parser : incomplete tokens\n");
			return (-1);
		}

		js = (json_t *)malloc(sizeof (json_t));
		extract(js->token1, tok->str, tok->indx + 1, indx - 1);

		/* setup the token list */
		if (o->curr) {
			o->curr->next = js;
		}
		if (!o->js) {
			o->js = js;
		}
		o->curr = js;
		tok->indx = indx + 1;
		return (val_sep_parser(tok, o));
	} else {
		printf("elem_parser : token recvd = %c, error\n", tok->str[tok->indx]);
	}

	printf("elem_parser : error\n");
	return (-1);
}

int
init_parser(token_t *tok, output_t *o)
{
	int	indx = tok->indx;

	strip_spaces(tok);

	if (tok->str[indx] == '{') {
		/* possible states are strings are present or end */
		tok->indx = indx + 1;
		if (elem_parser(tok, o) == 0) {
			printf("init_parser : elem_parser succeeded\n");
			return (0);
		}

		/* alternate path is close */
		if (end_parser(tok, o) == 0) {
			printf("init_parser : end_parser\n");
			return (0);
		}
	}

	printf("init_parser : incomplete tokens\n");
	return (-1);
}

int
main(int argc, char *argv[])
{
	token_t		tok;
	output_t	o;

	if (argc < 2) {
		printf("arguements needed\n");
		return (-1);
	}

	bzero(&o, sizeof (o));
	bzero(&tok, sizeof (tok));	

	tok.str = argv[1];
	tok.len = strlen(tok.str);

	if (init_parser(&tok, &o) == 0) {
		print_tokens(&o);
	} else {
		printf("unable to parse input\n");
	}
}
