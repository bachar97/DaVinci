function [textoutput]=  decryptfunc()
textoutput = '';
delimiter = '%';
pathencrypted = evalin('base','pathencrypted')
pathcover = evalin('base','pathcover')
clc
im1 = imread('stegotextencrypted.png');
im2 = imread(pathcover);
figure(1), imshow(im1); title('Encrypted Image');
figure(2)
imhist(im1)
figure(3), imshow(im2); title('Cover Image');
figure(4)
imhist(im2)
[l,w,b] = size(im1);

cover_size = l*w*b;

message = '';

char_stream = '';

i = 1; j = 1; k=1;

while (true)
   if (k > 3)
        k = 1;
        j = j + 1;
    end
    if (j > w)
       i = i + 1;
       j = 1;
    end
    
    if (i > l)
        break;
    end
    
    temp_pixel = dec2bin(im1(i, j, k), 8);
    LSB = temp_pixel(8);
    char_stream = strcat(char_stream, num2str(LSB));
    
    if length(char_stream) == 8
        new_char = char(bin2dec(char_stream));
        message = horzcat(message, new_char);
        if (new_char == delimiter)
            break;
        end
        char_stream = '';
    end
    
    k = k + 1;
end

secret_file = fopen('secret.txt','wb');
fwrite(secret_file, message);

fclose(secret_file);


disp(message);
A=imread(pathencrypted);
ref=imread(pathcover);

[peaksnr,snr]=psnr(A,ref);
err=immse(A,ref);
fprintf('\nThe PSNR value is : %0.4f',peaksnr);
fprintf('\nThe MSE value is : %0.4f\n',err);
x=  fix(err) 

if x == 0 && isnumeric(psnr(A,ref)) && psnr(A,ref) ~= Inf
textoutput = message
assignin('base','textoutput',textoutput) 

end
end