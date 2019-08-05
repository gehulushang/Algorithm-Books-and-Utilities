#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std; 

//已知进制和该进制数获得十进制数
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



//已知十进制数获得给定进制数

string func(int dec,int base){
	string ans  = "";
	int temp;
	
	while(dec>0){
		temp = dec%base;
		ans += to_string(temp);
		dec = dec/base;
		
	}
	return ans;
	 
}
int main(){
	
	int decimal,base;
	cin>>decimal>>base;
	string ans = func(decimal,base);
	
	cout<<ans;
	
	return 0;
	
}


















