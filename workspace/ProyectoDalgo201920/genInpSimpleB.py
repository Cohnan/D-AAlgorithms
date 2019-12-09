from random import randint

N = 10**3

print(N)

for i in range(N):
    #print(i+1, i, -i)
    print(i+1, randint(0, 10*N), randint(0, 10*N))

print(0)
