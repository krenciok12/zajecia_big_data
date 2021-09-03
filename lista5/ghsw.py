import matplotlib.pyplot as plt
import random
import math

class Bucket:
    def __init__(self, size, timestamp):
        self.size = size
        self.timestamp = timestamp
        self.next = None
        self.prev = None
        return

    def setNext(self, next):
        self.next=next
    def setPrevious(self, prev):
        self.prev=prev

n = 1000
N = 20

Last = 0
Total = 0
head = None
tail = None

for i in range(n):
    if tail != None:
        if tail.timestamp+N<=i:
            if tail==head:
                Size = 0
                Total = 0
                head = None
                tail = None
            else:
                Total-=tail.size
                tail = tail.prev
                tail.setNext(None)
                Last = tail.size

    r = random.randint(0,1)
    if r==0:
        continue

    Total+=1
    buck = Bucket(1,i)
    if head == None:
        head = buck
        tail = buck
        Size = 1
    else:
        buck.setNext(head)
        head.setPrevious(buck)
        head = buck

    tmp=head
    v = tmp.size
    j=1
    while tmp.next!=None:
        tmp = tmp.next
        if tmp.size == v:
            j+=1
            continue
        



    print(i,Total)
