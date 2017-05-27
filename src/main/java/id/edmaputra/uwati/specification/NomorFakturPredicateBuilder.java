package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QNomorFaktur;

public class NomorFakturPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}
	
	public void tanggal(Date tanggal){
		if (hasil == null){
			hasil = QNomorFaktur.nomorFaktur.tanggal.eq(tanggal);
		} else {
			hasil = hasil.and(QNomorFaktur.nomorFaktur.tanggal.eq(tanggal));
		} 
	}
	
	public void isSelesai(Boolean b){
		if (hasil == null){
			hasil = QNomorFaktur.nomorFaktur.isSelesai.eq(b);
		} else {
			hasil = hasil.and(QNomorFaktur.nomorFaktur.isSelesai.eq(b));
		}
	}
	
	public void isTerpakai(Boolean b){
		if (hasil == null){
			hasil = QNomorFaktur.nomorFaktur.isTerpakai.eq(b);
		} else {
			hasil = hasil.and(QNomorFaktur.nomorFaktur.isTerpakai.eq(b));
		}
	}
	
	

}
