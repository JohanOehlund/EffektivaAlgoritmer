%--------------------------------------------------------------------------
% Script: plot_result_effektiva
% Plots the time against the number of characters.
%--------------------------------------------------------------------------
% Authors:
% Johan Öhlund(c15jod@cs.umu.se)
%--------------------------------------------------------------------------
fileID = fopen('result6.txt','r');
formatSpec = '%d %d %f %f %f';
sizeA = [5 Inf];
A = fscanf(fileID,formatSpec,sizeA);
A=A';
steps=      A(:,1);
stepsNaive= A(:,2);
resNaive=   A(:,3)*0.000001;
resTopDown= A(:,4)*0.000001;
resBottomUp=A(:,5)*0.000001;
fclose(fileID);

plotNaive=plot(stepsNaive,resNaive);
grid on
title('Result (baaba)');
ylabel('Time (ms)');
xlabel('String length');
legend('Naive');

figure
title('Result - False (baaba)');
ylabel('Time (ms)');
xlabel('Nr of chars');
hold on

plotTopDown=plot(steps,resTopDown);
plotBottomUp=plot(steps,resBottomUp);

legend('TopDown','BottomUp');
grid on
hold off