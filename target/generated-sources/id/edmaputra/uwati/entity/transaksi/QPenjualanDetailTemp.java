package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPenjualanDetailTemp is a Querydsl query type for PenjualanDetailTemp
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPenjualanDetailTemp extends EntityPathBase<PenjualanDetailTemp> {

    private static final long serialVersionUID = -642139584L;

    public static final QPenjualanDetailTemp penjualanDetailTemp = new QPenjualanDetailTemp("penjualanDetailTemp");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final StringPath diskon = createString("diskon");

    public final StringPath dokter = createString("dokter");

    public final StringPath hargaJual = createString("hargaJual");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath jumlah = createString("jumlah");

    public final StringPath nomorFaktur = createString("nomorFaktur");

    public final StringPath obat = createString("obat");

    public final StringPath pajak = createString("pajak");

    public final StringPath pelanggan = createString("pelanggan");

    public final StringPath pengguna = createString("pengguna");

    public final StringPath subTotal = createString("subTotal");

    public final StringPath tanggal = createString("tanggal");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    public final NumberPath<Integer> tipe = createNumber("tipe", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPenjualanDetailTemp(String variable) {
        super(PenjualanDetailTemp.class, forVariable(variable));
    }

    public QPenjualanDetailTemp(Path<? extends PenjualanDetailTemp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPenjualanDetailTemp(PathMetadata<?> metadata) {
        super(PenjualanDetailTemp.class, metadata);
    }

}

