% Demo to show spline interpolation.
% Clean up / initialize
clc; 
close all;

fileID = fopen('result9.txt','r');
formatSpec = '%d %d %f %f %f';
sizeA = [5 Inf];

A = fscanf(fileID,formatSpec,sizeA);
A=A';
steps=      A(:,1);
%stepsNaive= A(:,2);
%resNaive=   A(:,3)*0.000001;
%resTopDown= A(:,4)*0.000001;
%resBottomUp=A(:,5)*0.000001;
resNaive=   A(:,3);
%resTopDown= A(:,4);
%resBottomUp=A(:,5);
fclose(fileID);

% Interpolate false
kx = max(steps);
k = steps == kx;
tmp = resNaive;
k = tmp(k);
k = mean(k);

stepsize = abs(steps(2)-steps(1));
% Should be O(n)
falsexponent1 = 1;
a1 = (k)./(kx.^falsexponent1);
kx1 = min(steps);
f1 = @(x) a1.*(x.^falsexponent1);
a1 = kx1:stepsize:max(steps);
y1 = arrayfun(f1,a1);

% Should be O(n^2)
falsexponent2 = 2;
a2 = (k)./(kx.^falsexponent2);
kx2 = min(steps);
f2 = @(x) a2.*(x.^falsexponent2);
a2 = kx2:stepsize:max(steps);
y2 = arrayfun(f2,a2);

% Should be O(n^3)
falsexponent3 = 3;
a3 = (k)./(kx.^falsexponent3);
kx3 = min(steps);
f3 = @(x) a3.*(x.^falsexponent3);
a3 = kx3:stepsize:max(steps);
y3 = arrayfun(f3,a3);

% Should be O(2^n)
a4 = (k)./(2.^kx);
kx4 = min(steps);
f4 = @(x) a4.*(2.^x);
a4 = kx4:stepsize:max(steps);
y4 = arrayfun(f4,a4);

% Should be O(n*2^n)
a5 = (k)./(kx.*(2.^kx));
kx5 = min(steps);
f5 = @(x) a5.*(x.*(2.^x));
a5 = kx5:stepsize:max(steps);
y5 = arrayfun(f5,a5);


plotResult=plot(steps,y2,steps,y3,steps,y5,steps,resNaive ,'o');

title('Result - False, Parentheses ()).');
ylabel('Operations');
xlabel('String length');
legend('Quadratic','Cubic','n*2^n','Naive');
grid on