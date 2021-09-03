import matplotlib.pyplot as plt
import random
import math

def counter(n):
    k=1
    arr = []
    for i in range(n+1):
        r = random.randint(1,math.pow(2,k))
        if (r==1):
            k+=1
        arr.append(k)
    return arr

n=100000

ar1 = counter(n)
ar2 = counter(n)
ar3 = counter(n)
ar4 = counter(n)

ar = []
for i in range(n+1):
    ar.append(math.pow(2,(ar1[i]+ar2[i]+ar3[i]+ar4[i])/4))

plt.plot(range(n+1),ar)
plt.plot(range(n+1),range(n+1))
plt.show()
