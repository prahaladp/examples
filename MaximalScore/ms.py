
def add_vertices(v, indx):
    v.insert(indx, {})

def add_edge(vert, u, v, w):
    vm = vert[u]
    vm[str(v)] = w

def read_edges(num_edge, v):
    for i in range(0, num_edge):
            edge = raw_input('enter edge : ')
            tok = edge.split(' ')
            add_edge(v, int(tok[0]), int(tok[1]), int(tok[2]))

def create_final_path(total_vertices):
    cp = []
    for i in range(0, total_vertices):
        cp.insert(i, [])
        cprow = cp[i]
        for j in range(0, total_vertices):
            cprow.insert(j, 0);

    return cp

def get_path_lengths(u, v, vert, path_mat):

    src = path_mat[u]
    if src[dst
    vmap = vert[u]
    if no vmap:
        return None

    path = []

    # iterate on all the connected edges
    for edge in vmap:
        edgeint = int(edge)
        if edgeint == v:
            path.append(vmap[edge])
        else:
            sub_path = get_path_lengths(edgeint, v, vert)
            complete_path.append(edgeint)
            complete_path.extend(sub_path)
            path.append(complete_path)

    return path


def compute_path_score(vert, total_vertices):
    ps = []
    for i in range(0, total_vertices):
        ps.insert(i, [])
        psv = ps[i]
        v = vert[i]
        for j in range(0, total_vertices):
            if not v:
                psv[j] = 0
                continue

            if i == j:
                psv[j] = 0
                continue

def compute_paths(total_vertices, vert_dict, final_path):
    print 'computing path'

    for i in range(0, total_vertices):
        # see if there is a directed path first
        v = vert_dict[i]

        if v.empty():
            # this is not connected
            continue

        for j in range(0, total_vertices):
            if i == j:
                continue;

            if str(j) in v:
                min_path = v[str(j)]

            # see if there are any other paths

first = raw_input()

tok = first.split(' ')
total_vertices = int(tok[0])
total_edges = int(tok[1])

v = []
for i in range(0, total_vertices):
    print 'adding vertices'
    add_vertices(v, i)

print 'vertices : ' + v

# read the edges
read_edges(total_edges, v)

print 'path matrix : ' + v

cp = create_final_path(total_vertices)

# start computation


