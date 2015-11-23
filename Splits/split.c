#include <stdio.h>
#include <string.h>

typedef struct split_s {
	int	start;
	int	mark;
	int	end;
	char	*str;
	int	len;
} split_t;

int
split(split_t *sp)
{
	int	start = sp->start;
	int	mark = sp->end;
	int	end = sp->end;
	char	*str = sp->str;
	int	len = sp->len;
	int	cnt;

	printf("split : %s,%d (%d, %d)\n", sp->str, sp->len,
		sp->start, sp->end);

	if (start >= end) {
		return (1);
	}

	cnt = (int)(-1);

	while (start < end) {
		if (sp->str[start] != sp->str[end]) {
			break;
		}
		start++;
		end--;
	}

	if (start >= end) {
		printf("palindrome : (%d->%d)\n", sp->start, sp->end);
		return (1);
	}

	start = sp->start;
	end = sp->end;
	mark = sp->end;

	while (mark > start) {

		split_t	r;
		split_t l;
		int	sp_cnt;
		int	rsplit, lsplit;

		bzero(&r, sizeof (r));
		bzero(&l, sizeof (l));

		r.str = str;
		r.len = len;

		l.str = str;
		l.len = len;

		r.start = start;
		r.end = mark - 1;

		l.start = mark;
		l.end = end;
		
		printf("split : (%d->%d), (%d->%d)\n",
			r.start, r.end,
			l.start, l.end);	
		rsplit = split(&r);
		lsplit = split(&l);
		printf("split : (r=%d) (l=%d)\n", rsplit, lsplit);
		sp_cnt = rsplit + lsplit;
		if (sp_cnt < cnt) {
			cnt = sp_cnt;
		}

		mark--;
	}

	return (cnt);
}

int
main(int argc, char **argv)
{
	split_t		sp;

	bzero(&sp, sizeof (sp));
	sp.str = argv[1];
	sp.len = strlen(sp.str);
	sp.start = 0;
	sp.end = sp.len - 1;
	printf("Total Split = %d\n", split(&sp));
}
