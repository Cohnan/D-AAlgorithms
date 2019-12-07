import time
from collections import defaultdict, Counter

def get_suffix_array(str):
    return sorted(range(len(str)), key=lambda i: str[i:])

def suffix_array_ManberMyers(stri):
    result = []
    def sort_bucket(stri, bucket, order=1):
        d = defaultdict(list)
        for i in bucket:
            key = stri[i:i+order]
            d[key].append(i)
        #print('d', bucket, order, d)
        for k, v in sorted(d.items()):
            if len(v) > 1:
                sort_bucket(stri, v, order*2)
            else:
                result.append(v[0])
        #print("result", result)
        return result

    return sort_bucket(stri, [i for i in range(len(stri))])	    

if __name__ == "__main__":
    #with open("RText"+input()+".txt") as f:
    #    m = f.read()
    #stri = m#[:100000]
    stri = "a"*(2**int(input()))#	'banana'
    #print(len(stri))
    ##start_time = time.time()
    ##x = get_suffix_array(stri)
    ##end_time = time.time()
    ##print(x)
    ##print("Time for python sort was %g seconds" % (end_time - start_time))
    start_time = time.time()
    y = suffix_array_ManberMyers(stri)
    end_time = time.time()
    #assert(x == y)
#    print(y)
    print("Time for Manber Myers was %g seconds" % (end_time - start_time))
