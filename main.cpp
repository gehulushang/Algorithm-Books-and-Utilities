#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std; 

//进制转换函数
long long convert(string n, long long radix) {    
	long long sum = 0;    
	int index = 0, temp = 0;    
	for (auto it = n.rbegin(); it != n.rend(); it++) {        
	temp = isdigit(*it) ? *it - '0' : *it - 'a' + 10;       
		sum += temp * pow(radix, index++);   
    }    
	return sum; 
} 

int main(){
	
	string num;
	int radix;
	cin>>num>>radix;
	
	int ans = convert(num,radix);
	cout<<ans;
	
	return 0;
}




















