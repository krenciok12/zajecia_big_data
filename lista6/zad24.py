from hll import HyperLogLog
import random
import numpy
import matplotlib.pyplot as plt

def h1(s):
        res="{:063b}".format(abs(hash(s)))
        res=(res[:37])
        return res


al = 32

alfa={16:0.673, 32:0.697, 64:0.709}

s = []
d= []
sd = []

with open("lbl-pkt-4.tcp") as fp:
    L=fp.readlines()
    for el in L:
        e = el.split()
        s.append(e[1])
        d.append(e[2])
        sd.append(e[1]+" "+e[2])

new = HyperLogLog(s,h1,alfa[al],al)
print(new)
print(len(set(s)),"\n")

new = HyperLogLog(d,h1,alfa[al],al)
print(new)
print(len(set(d)),"\n")

new = HyperLogLog(sd,h1,alfa[al],al)
print(new)
print(len(set(sd)),"\n")
