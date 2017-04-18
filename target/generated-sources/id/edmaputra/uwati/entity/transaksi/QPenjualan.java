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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPenjualan penjualan = new QPenjualan("penjualan");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<java.math.BigDecimal> bayar = createNumber("bayar", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> diskon = createNumber("diskon", java.math.BigDecimal.class);

    public final id.edmaputra.uwati.entity.master.QDokter dokter;

    public final NumberPath<java.math.BigDecimal> grandTotal = createNumber("grandTotal", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final NumberPath<java.math.BigDecimal> kembali = createNumber("kembali", java.math.BigDecimal.class);

    public final StringPath nomorFaktur = createString("nomorFaktur");

    public final StringPath nomorResep = createString("nomorResep");

    public final NumberPath<java.math.BigDecimal> pajak = createNumber("pajak", java.math.BigDecimal.class);

    public final id.edmaputra.uwati.entity.master.QPelanggan pelanggan;

    public final id.edmaputra.uwati.entity.pengguna.QPengguna pengguna;

    public final ListPath<PenjualanDetail, QPenjualanDetail> penjualanDetail = this.<PenjualanDetail, QPenjualanDetail>createList("penjualanDetail", PenjualanDetail.class, QPenjualanDetail.class, PathInits.DIRECT2);

    public final ListPath<PenjualanRacikan, QPenjualanRacikan> penjualanRacikan = this.<PenjualanRacikan, QPenjualanRacikan>createList("penjualanRacikan", PenjualanRacikan.class, QPenjualanRacikan.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    public final NumberPath<Integer> tipe = createNumber("tipe", Integer.class);

    public final NumberPath<java.math.BigDecimal> totalPembelian = createNumber("totalPembelian", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public final DatePath<java.util.Date> waktuTransaksi = createDate("waktuTransaksi", java.util.Date.class);

    public QPenjualan(String variable) {
        this(Penjualan.class, forVariable(variable), INITS);
    }

    public QPenjualan(Path<? extends Penjualan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPenjualan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPenjualan(PathMetadata<?> metadata, PathInits inits) {
        this(Penjualan.class, metadata, inits);
    }

    public QPenjualan(Class<? extends Penjualan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dokter = inits.isInitialized("dokter") ? new id.edmaputra.uwati.entity.master.QDokter(forProperty("dokter")) : null;
        this.pelanggan = inits.isInitialized("pelanggan") ? new id.edmaputra.uwati.entity.master.QPelanggan(forProperty("pelanggan")) : null;
        this.pengguna = inits.isInitialized("pengguna") ? new id.edmaputra.uwati.entity.pengguna.QPengguna(forProperty("pengguna")) : null;
    }

}

