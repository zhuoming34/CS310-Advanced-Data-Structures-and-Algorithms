// non-tail recursive
int sumofsquare (int n) {
	if (n == 1)
		return 1;
	else
		return (n * n + sumofsquare(n-1)); 
}