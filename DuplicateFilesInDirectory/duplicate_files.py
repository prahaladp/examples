import os
import sys
import hashlib

def traverse(root_path):
    count = 0
    filehash = {}
    for root, dirs, files in os.walk(root_path):  
        for filename in files:
            count = count + 1
            complete_path = os.path.join(root, filename)
            hd = hashlib.md5(open(complete_path,'rb').read()).hexdigest()
            if hd not in filehash:
                filehash[hd] = [complete_path]
            else:
                filehash[hd].append(complete_path)

    for key, value in filehash.items():
        if len(value) > 1:
            print value
    print "found total : ", count
    print "dictionary size : ", len(filehash)

def main():
    if len(sys.argv) <= 1:
        print "please enter the starting path"
        exit(1)
    traverse(sys.argv[1])

if __name__ == "__main__":
    main()

