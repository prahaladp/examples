#/usr/bin/python

import sys
import os
import getopt

def add_to_ext_files(dict, year, path, file):
    if year not in dict:
        dict[year] = []
    dict[year].append([path, file])

def build_list(dict, root, ext):
    dict = {}
    for dirname, dirnames, filenames in os.walk(root):
        for f in filenames:
            for e in ext:
                if e in f.lower():
                    year = dirname
                    path = dirnames
                    file = f

                    # add it to the list of valid files
                    add_to_ext_files(dict, year, path, file)

    return dict

def display(dict):
    for root in dict:
        val = dict[root]
        for v in val:
            if len(v[0]) is not 0:
                print '%s/%s/%s' % (root, v[0], v[1])
            else:
                print '%s/%s' % (root, v[1])

def check_dest(dst, filename):
    for rpath in dst:
        all_files = dst[rpath]
        for path in all_files:
            if filename.lower() in path[1].lower():
                return True
    return False

def copy_files(file_list):
    print 'files to be copied'
    for f in file_list:
        print '--->' + f

    print 'total count : ' + str(len(file_list))

def compare(in_dict, out_dict):
    list_files_to_copy = []
    for rpath in in_dict:
        all_files = in_dict[rpath]
        for path in all_files:
            filename = path[1]
            if check_dest(out_dict, filename) is False:
                if len(path[0]) is not 0:
                    src_path = '%s/%s/%s' % (rpath, path[0], path[1])
                else:
                    src_path = '%s/%s' % (rpath, path[1])
            list_files_to_copy.append(src_path)

    copy_files(list_files_to_copy) 

def move_files(in_dir, out_dir, ext):
    in_dict = {}
    in_dict = build_list(in_dict, in_dir, ext)
    out_dict = {}
    out_dict = build_list(out_dict, out_dir, ext)

    #display(in_dict)

    compare(in_dict, out_dict)

def get_extensions(dir):
    dict = {}
    ignored = 0
    for dirname, dirnames, filenames in os.walk(dir):
        for f in filenames:
            tok = f.split('.')
            if len(tok) >= 2:
                if tok[1] not in dict:
                    dict[tok[1]] = 0
                val = dict[tok[1]]
                dict[tok[1]] = val + 1
            else:
                ignored = ignored + 1

    for k in dict:
        print 'ext : *.' + k + ' : ' + str(dict[k])

    print 'ignored count ' + str(ignored)


def main(argv):
    ext = ['mov', 'avi', 'mp4', 'wmv']
    in_dir = None
    out_dir = None
    try:
        opts, args = getopt.getopt(argv[1:], "i:o:", ["ifile=","ofile="])
    except:
        print 'enumerateandcopy.py -i <inputdir> -o <outputdir>'
        sys.exit(2)

    print opts
    for opt, arg in opts:
        if opt in ("-i", "--ifile"):
            in_dir = arg
            print in_dir
        elif opt in ("-o", "--ofile"):
            out_dir = arg
            print out_dir

    if in_dir is None or out_dir is None:
        print 'need input dir and output dir\n'
        sys.exit(2)

    print 'moving files'
    get_extensions(in_dir)
    get_extensions(out_dir)

    move_files(in_dir, out_dir, ext)

if __name__ == "__main__":
    main(sys.argv)
