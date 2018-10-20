%--------------------------------------------------------------------------
% Script: plot_result_effektiva
% Plots the time against the number of characters.
%--------------------------------------------------------------------------
% Authors:
% Johan Öhlund(c15jod@cs.umu.se)
%--------------------------------------------------------------------------
func_nlog=@(x) x.*log(x);
func_linear=@(x) x;
func_kvad=@(x) x.^2;
func_qubic=@(x) x.^3;
fileID = fopen('result9.txt','r');
formatSpec = '%d %d %f %f %f';
sizeA = [5 Inf];

A = fscanf(fileID,formatSpec,sizeA);
A=A';
steps=      A(:,1);
stepsNaive= A(:,2);
%resNaive=   A(:,3)*0.000001;
%resTopDown= A(:,4)*0.000001;
%resBottomUp=A(:,5)*0.000001;
resNaive=   A(:,3);
resTopDown= A(:,4);
resBottomUp=A(:,5);
fclose(fileID);

plotNaive=plot(stepsNaive,resNaive);
grid on
title('Result - False Parentheses');
ylabel('Operaions');
xlabel('String length');
legend('Naive');

figure
title('Result - False Parentheses');
ylabel('Operaions');
xlabel('String length');
hold on

plotTopDown=plot(steps,resTopDown);
plotBottomUp=plot(steps,resBottomUp);
%plotLinear = plot(steps,func_linear(steps));
%plotKvad = plot(steps,func_kvad(steps));
plotQubic = plot(steps,func_qubic(steps));
legend('TopDown','BottomUp','Qubic');
grid on
hold off