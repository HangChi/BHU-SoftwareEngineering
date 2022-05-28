clear;
close all;
clc;
pho=[300;862;74.2;1.18];
c=[1377;2100;1726;1005];
lamda=[0.082;0.37;0.045;0.028];
a=lamda./(pho.*c);

d=[0.6;6;3.6;5]*10^-3;

TT=273.15;
T_in=37;
T_out=75;
T_s=48.08;

xmin=0;
xmax=sum(d);

N=5400;
h=0.05*10^-3;
k=1;
r=k/h^2;
I=round((xmax-xmin)/h);

A=zeros(1,I);
B=zeros(1,I+1);
C=zeros(1,I);

N1=round(d(1)/h);
N2=round(d(2)/h);
N3=round(d(3)/h);
N4=round(d(4)/h);

for i=1:N1
    A(i)=-a(1)*r;
    B(i)=2+2*r*a(1);
    C(i)=-r*a(1);
end
for i=N1+1:N1+N2
    A(i)=-a(2)*r;
    B(i)=2+2*r*a(2);
    C(i)=-r*a(2);
end
for i=N1+N2+1:N1+N2+N3
    A(i)=-a(3)*r;
    B(i)=2+2*r*a(3);
    C(i)=-r*a(3);
end
for i=N1+N2+N3+1:N1+N2+N3+N4
    A(i)=-a(4)*r;
    B(i)=2+2*r*a(4);
    C(i)=-r*a(4);
end

T=zeros(I+1,N+1);
T(:,1)=(T_in+TT)*ones(I+1,1);

T_xt=xlsread('1.xlsx');

h_min=110;
h_max=120;
delta_h=0.1;
H1=h_min:delta_h:h_max;
delta=zeros(1,length(H1));

for j=1:length(H1)
    h1=h_min+(j-1)*delta_h;
    k1=lamda(1);k2=lamda(2);k3=lamda(3);k4=lamda(4);
    x1=d(1);x2=d(1)+d(2);x3=d(1)+d(2)+d(3);x4=d(1)+d(2)+d(2)+d(4);
    t1=T_out+TT;t2=T_in+TT;t3=T_s+TT;
    
    h5=-((h1*k2*k3*k4*t1)/(k1*k2*k3*k4-h1*k1*k2*k3*x3-h1*k1*k2*k4*x2 ...
        -h1*k1*k3*k4*x1+h1*k1*k2*k3*x4+h1*k1*k2*k4*x3+h1*k1*k3*k4*x2+h1*k2*k3*k4*x1)-(h1*k2*k3*k4*t3)...
        /(k1*k2*k3*k4-h1*k1*k2*k3*x3-h1*k1*k2*k4*x2-h1*k1*k3*k4*x1+h1*k1*k2*k3*x4+h1*k1*k2*k4*x3+h1*k1*k3*k4*x2+h1*k2*k3*k4*x1))...
        /(t2/k1-t3/k1);
    
    AA=diag(B)+diag(A,1)+diag(C,-1);
    AA(1,1)=lamda(1)/h+h1;
    AA(1,2)=-lamda(1)/h;
    AA(I+1,I)=-lamda(4)/h;
    AA(I+1,I+1)=lamda(4)/h+h5;
    
    AA(N1+1,N1)=-lamda(1);
    AA(N1+1,N1+1)=lamda(1)+lamda(2);
    AA(N1+1,N1+2)=-lamda(2);
    
    AA(N1+N2+1,N1+N2)=-lamda(2);
    AA(N1+N2+1,N1+N2+1)=lamda(2)+lamda(3);
    AA(N1+N2+1,N1+N2+2)=-lamda(3);
    
    AA(N1+N2+N3+1,N1+N2+N3)=-lamda(3);
    AA(N1+N2+N3+1,N1+N2+N3+1)=lamda(3)+lamda(4);
    AA(N1+N2+N3+1,N1+N2+N3+2)=-lamda(4);
    
    for n=1:k:N
        D=zeros(I+1,1);
        D(1)=h1*(T_out+TT);
        D(I+1)=h5*(T_in+TT);
        for i=2:1:N1
            D(i)=r*a(1)*T(i-1,n)+(2-2*r*a(1))*T(i,n)+r*a(1)*T(i+1,n);
        end
        for i=N1+1:1:N1+N2
            D(i)=r*a(2)*T(i-1,n)+(2-2*r*a(2))*T(i,n)+r*a(2)*T(i+1,n);
        end
        for i=N1+N2+1:1:N1+N2+N3
            D(i)=r*a(3)*T(i-1,n)+(2-2*r*a(3))*T(i,n)+r*a(3)*T(i+1,n);
        end
        for i=N1+N2+N3+1:1:N1+N2+N3+N4
            D(i)=r*a(4)*T(i-1,n)+(2-2*r*a(4))*T(i,n)+r*a(4)*T(i+1,n);
        end
        D(N1+1)=0;
        D(N1+N2+1)=0;
        D(N1+N2+N3+1)=0;
        T(:,n+1)=AA\D;
    end
   delta(j)=sqrt(sum((T_xt(:,2)-T(end,:)'+TT).^2)/length(T_xt(:,1)));
end
%Í¼¶þ 
figure(1);
mesh(0:k:N,1000*(0:h:sum(d)),(T-TT));
%Í¼Èý
T_problem1=zeros(N+1,4);
T_problem1(:,1)=T(1,:)';
T_problem1(:,2)=T(N1+1,:)';
T_problem1(:,3)=T(N2+N1+1,:)';
T_problem1(:,4)=T(N3+N2+N1+1,:)';
T_problem1=T_problem1-TT;
figure(2);
plot(0:k:N,T_problem1(:,1)',0:k:N,T_problem1(:,2)',0:k:N,T_problem1(:,3)',0:k:N,T_problem1(:,4)',0:k:N,T_xt(:,2)');


        
