import urllib
import keras
from tensorflow.keras.models import load_model
import tensorflow as tf
from tensorflow import convert_to_tensor
from keras.preprocessing.image import load_img
from keras.preprocessing.image import load_img

from keras.preprocessing.image import img_to_array
from keras.applications.imagenet_utils import decode_predictions
import matplotlib.pyplot as plt
import cv2
import numpy as np
gpus = tf.config.experimental.list_physical_devices('GPU')
if gpus:
    try:
        # Currently, memory growth needs to be the same across GPUs
        for gpu in gpus:
            tf.config.experimental.set_memory_growth(gpu, True)
        logical_gpus = tf.config.experimental.list_logical_devices('GPU')
        #print(len(gpus), "Physical GPUs,", len(logical_gpus), "Logical GPUs")
    except RuntimeError as e:
        # Memory growth must be set before GPUs have been initialized
        print(e)

def url_to_image(url):
	# download the image, convert it to a NumPy array, and then read
	# it into OpenCV format
	resp = urllib.request.urlopen(url)
	image = np.asarray(bytearray(resp.read()), dtype="uint8")
	image = cv2.imdecode(image, cv2.IMREAD_COLOR)
	# return the image
	return image
file = open('/home/elidor/IdeaProjects/Covid/theBridge.txt')
path = str(file.readlines())
path =path.replace('[','')
path =path.replace(']','').replace("'", "").replace('\\n','')
url =path
#print ("Downloading: %s" % (url))
image = cv2.resize(url_to_image(url),(120,120))
original =  image

image = cv2.resize(url_to_image(url),(120,120))
original =  image

#print('PIL image size', original.size)
#plt.imshow(original)
#plt.show()

# convert the PIL image to a numpy array
# IN PIL - image is in (width, height, channel)
# In Numpy - image is in (height, width, channel)

numpy_image = img_to_array(original)/255.0


#plt.imshow(np.uint8(numpy_image))

#plt.show()

#print('numpy array size', numpy_image.shape)



# Convert the image / images into batch format

# expand_dims will add an extra dimension to the data at a particular axis

# We want the input matrix to the network to be of the form (batchsize, height, width, channels)

# Thus we add the extra dimension to the axis 0
image_batch = np.array([numpy_image])

#print('image batch size', image_batch.shape)

#plt.imshow(image_batch[0])
with tf.device('/cpu:0'):
    model = load_model('/home/elidor/test/testmodel.h5')
    prob = model.predict(image_batch)

print(prob)


