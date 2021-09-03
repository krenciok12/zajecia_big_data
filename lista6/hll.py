import math

def phi(tab):
    for i in range(len(tab)):
        if tab[i]=='1':
            return i+1
    return len(tab)+1

def numbzeros(tab):
    numb=0
    for i in tab:
        if i==0:
            numb+=1
    return numb

def HyperLogLog(Multi,h,alfa,m):
    b = math.log(m,2)
    M=[]
    for i in range(m):
        M.append(0)

    for v in Multi:
        x=(h(v))
        j=int(x[:int(b)],2)
        w=x[int(b):]
        M[j]=max(M[j],phi(w))

    E=alfa*m*m / sum([math.pow(2,(-1)*i)  for i in M])

    if E<=5/2*m:
        v=numbzeros(M)
        if v!=0:
            return m*math.log(m/v,2)
        else:
            return E
    if E<=1/30*math.pow(2,32):
        return E
    if E>1/30*math.pow(2,32):
        return -math.pow(2,32)*math.log(1-E/math.pow(2,32))
