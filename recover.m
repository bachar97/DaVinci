function recoveredimage= recover()
patheimagefordecryption = evalin('base','patheimagefordecryption');
limage= imread(patheimagefordecryption);

h=mod(limage,2);
p=zeros(1000,1000);

for x=1:1000
    for y=1:1000
        if(h(x,y)==1)
            p(x,y)=255;
        end
    end
end

s=im2bw(p);

imwrite(s, 'recoveredimage.png');
recoveredimage = "../recoveredimage.png";
assignin('base','recoveredimage',recoveredimage)
figure;
imshow(s); 

imhist(s)
title('Recovered hidden image')
end