import matplotlib.pyplot as plt
import numpy as np
from scipy.interpolate import spline

import math
import random

x, y = np.loadtxt('WTA.txt', delimiter=',', unpack=True)
# a, b = np.loadtxt('trainedData3A.txt', delimiter=',', unpack=True)
a, b = np.loadtxt('initWeight.txt', delimiter=',', unpack=True)
j, k = np.loadtxt('finalWeight.txt', delimiter=',', unpack=True)


plt.scatter(x, y, s = 5)
plt.scatter(a,b, marker= "*",s = 20, label="initialWeight")
plt.scatter(j,k, marker= "p", label="finalWeight")

#plt.plot(a,b,'red')
# plt.plot(x_smooth, y_smooth, 'red')

plt.xlabel('x')
plt.ylabel('y')
# plt.title('WTA graph')
# plt.legend(recs, label_dict)
plt.legend(bbox_to_anchor=(0., 1.02, 1., .102), loc=3,
           ncol=2, mode="expand", borderaxespad=0.)
           
plt.grid(True)
plt.show()