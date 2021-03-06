package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPembelianDetailTemp is a Querydsl query type for PembelianDetailTemp
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPembelianDetailTemp extends EntityPathBase<PembelianDetailTemp> {

    private static final long serialVersionUID = -943136887L;

    public static final QPembelianDetailTemp pembelianDetailTemp = new QPembelianDetailTemp("pembelianDetailTemp");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final StringPath diskon = createString("diskon");

    public final StringPath hargaBeli = createString("hargaBeli");

    public final StringPath hargaJual = createString("hargaJual");

    public final StringPath hargaJualResep = createString("hargaJualResep");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idObat = createNumber("idObat", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath jumlah = createString("jumlah");

    public final StringPath nomorFaktur = createString("nomorFaktur");

    public final StringPath obat = createString("obat");

    public final StringPath pajak = createString("pajak");

    public final StringPath pengguna = createString("pengguna");

    public final StringPath pesan = createString("pesan");

    public final StringPath randomId = createString("randomId");

    public final StringPath subTotal = createString("subTotal");

    public final StringPath supplier = createString("supplier");

    public final StringPath tanggal = createString("tanggal");

    public final StringPath tanggalKadaluarsa = createString("tanggalKadaluarsa");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPembelianDetailTemp(String variable) {
        super(PembelianDetailTemp.class, forVariable(variable));
    }

    public QPembelianDetailTemp(Path<? extends PembelianDetailTemp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPembelianDetailTemp(PathMetadata<?> metadata) {
        super(PembelianDetailTemp.class, metadata);
    }

}

