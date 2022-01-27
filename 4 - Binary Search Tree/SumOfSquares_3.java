// tail-recursive
int sumofsquare (int n) {
	return helper(n,1);
}
int helper (int n, int sum) {
	if (n > 1) {
		return helper(n - 1, n * n + sum);
	}
	return sum;
}
