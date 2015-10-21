//============================================================================
// Name        : testcpp.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include<string>
#include<sstream>
#include<string.h>
#include <stdio.h>
using namespace std;

int addition(int a, int b) {
	return (a + b);
}

int subtraction(int a, int b) {
	return (a - b);
}

int (*minu)(int, int)=subtraction;

int operation(int x, int y, int (*functocall)(int, int)) {
	int g;
	g = (*functocall)(x, y);
	return (g);
}

int main() {

//	string str;
//	int quantity;
//	float price;
//	cout<<"enter the price: ";
//	getline(cin,str);
//	stringstream(str)>>price;
//	cout<<"enter the quantity: ";
//	getline(cin,str);
//	stringstream(str)>>quantity;
//	cout<<"Total price: "<<price*quantity<<endl;

//	const int x = 30;
//	if (x > 100) {
//		cout << "x is biger than 100" << endl;
//	} else if (x > 30) {
//		cout << "x is bigger than 30" << endl;
//	} else {
//		cout << "x is less than 30" << endl;
//	}

//	int n;
//	cout<<"enter the start number: ";
//	cin>>n;
//	while(n>0){
//		cout<<n<<endl;
//		n--;
//	}
//	cout<<"fire!"<<endl;

//	int n;
//		cout<<"enter the start number: ";
//		cin>>n;
//	do{
//		cout<<n<<endl;
//		n--;
//	}while(n>0);
//	cout<<"end!!"<<endl;

//	char str[]="hello";
//	cout<<str[3];

//	char str[10];
//	strcpy(str,"lixuejiao");
//	cout<<str;

//	char str[20];
//	cout<<"what is your name: ";
//	cin.getline(str,20);
//	cout<<str<<endl;

//	int x=5,y=6;
//	int *p1,*p2;
//	p1=&x;
//	p2=&y;
//	*p1=10;//p1所指向的值变为10;
//
//	cout<<x<<" "<<y<<endl;
//	*p2=*p1;//p2所指向的值等于p1所指向的值，等于10
//	cout<<x<<" "<<y<<endl;
//
//	p1=p2;//p1指向p2，
//	*p1=20;//p1指向的值变为20  即p2指向的值变为20
//	cout<<x<<" "<<y<<endl;

	int m, n;
	m = operation(7, 5, addition);
	cout << m << endl;
	n = operation(20, m, minu);
	cout << n << endl;
	return 0;
}

