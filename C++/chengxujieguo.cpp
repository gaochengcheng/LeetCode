#include <iostream>
#include <stdio.h>
using namespace std;

int main(){
	char *str[] = {"welcome","to","our","company"};
	char **p[] = {str+1,str+3,str+2,str};
	char ***pp = p;
	printf("%s",*--*++pp+1);
	cout<<"a	"<<endl;
}
