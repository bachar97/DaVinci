function imageencrypted= embed()

clc;

close all;
imagepathencrypted=evalin('base','imagepathencrypted')
i=imread(imagepathencrypted);
j=imresize(i,[1000, 1000]);  
k=rgb2gray(j); 


figure
subplot(1,2,1)
imshow(i);
title('Objective image');
embedimagepathencrypted = evalin('base','embedimagepathencrypted')
x=imread(embedimagepathencrypted); 


y=imresize(x,[1000, 1000]); 
z=im2bw(y); 


subplot(1,2,2);
imshow(x) 
title('Objective image');
title('image to be hidden');

z=double(z); 

r=double(k-mod(k,2)); 
l=uint8(r+z);

imwrite(l, 'imageencrypted.png');
imageencrypted = '../imageencrypted.png';
assignin('base','imageencrypted',imageencrypted)
figure
imshow(l)
imhist(l)
title('Invisble watermarked Image'); 
end