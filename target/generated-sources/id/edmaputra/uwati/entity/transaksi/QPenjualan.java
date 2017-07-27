package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPenjualan is a Querydsl query type for Penjualan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPenjualan extends EntityPathBase<Penjualan> {

    private static final long serialVersionUID = 1781711899L;

    public static final QPenjualan penjualan = new QPenjualan("penjualan");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<java.math.BigDecimal> bayar = createNumber("bayar", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> diskon = createNumber("diskon", java.math.BigDecimal.class);

    public final StringPath dokter = createString("dokter");

    public final NumberPath<java.math.BigDecimal> grandTotal = createNumber("grandTotal", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final NumberPath<java.math.BigDecimal> kembali = createNumber("kembali", java.math.BigDecimal.class);

    public final StringPath nomorFaktur = createString("nomorFaktur");

    public final StringPath nomorResep = createString("nomorResep");

    public final NumberPath<java.math.BigDecimal> pajak = createNumber("pajak", java.math.BigDecimal.class);

    public final StringPath pelanggan = createString("pelanggan");

    public final StringPath pengguna = createString("pengguna");

    public final ListPath<PenjualanDetail, QPenjualanDetail> penjualanDetail = this.<PenjualanDetail, QPenjualanDetail>createList("penjualanDetail", PenjualanDetail.class, QPenjualanDetail.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    public final NumberPath<Integer> tipe = createNumber("tipe", Integer.class);

    public final NumberPath<java.math.BigDecimal> totalPembelian = createNumber("totalPembelian", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public final DatePath<java.util.Date> waktuTransaksi = createDate("waktuTransaksi", java.util.Date.class);

    public QPenjualan(String variable) {
        super(Penjualan.class, forVariable(variable));
    }

    public QPenjualan(Path<? extends Penjualan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPenjualan(PathMetadata<?> metadata) {
        super(Penjualan.class, metadata);
    }

}

