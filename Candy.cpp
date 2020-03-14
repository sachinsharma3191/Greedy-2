#include<iostream>
#include<stack>
#include<algorithm>
#include<vector>
using namespace std;

// Time Complexity : O(N) 
// Space Complexity : O(N) 
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : NO
// Your code here along with comments explaining your approach

/*
Approach
Take auxillary array result for maintaing the distribution of candy
Now start Iterating array from index 1.
let current_elem = array[i];
let left_neigh  = array[i -1];
 if current_elem > left_neigh then
    result[i] = result[i] + 1
Keep repeating until all elements are processed

Now start array traversal from end;
let curr_elem = array[i];
let righ_neigh = array[i + 1];
 if current_elem > right_neigh then
    result = max(result[i] + 1,result[i]) + 1
Keep repeating until all elements are processed

So 2 Iterations will be processed for Finding Max Candy

*/
int candy(vector<int>& ratings) {
     int size = ratings.size();
    if(size == 0)
        return 0;
    vector<int> array(ratings.size());
   
    fill(array.begin(),array.end(),1);
    //Left Pass
    for(int i =1; i < size; i++){
        if(ratings[i] > ratings[i - 1]){
            array[i] = array[i- 1 ] + 1;
        }
    }
    //Right Pass
    int sum = 0;
    for(int i = size - 2; i >= 0; i--){
        if(ratings[i] > ratings[i + 1]){
            array[i] =  max(array[i],array[i  + 1] +1);
        }
    }
    
    for(int num : array){
        sum += num;
    }
    return sum; 
}

int main(){
    vector<int> ratings = {1,0,2};

    cout<<candy(ratings);
}