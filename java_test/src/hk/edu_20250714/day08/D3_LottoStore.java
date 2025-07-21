package hk.edu_20250714.day08;

public class D3_LottoStore {
	public D3_Lotto[] lottoStore;

	public D3_LottoStore() {
		this(5);
	}

	public D3_LottoStore(int count) {
		lottoStore = new D3_Lotto[count];

		for (int i=0; i<count; i++) {
			lottoStore[i] = new D3_Lotto();
		}

	}
}