// iterative
int sumofsquare (int n) {
	int i = 1;
	int sum = 0;
	while (i <= n) {
		sum += (i * i);
		i += 1;
	}
}