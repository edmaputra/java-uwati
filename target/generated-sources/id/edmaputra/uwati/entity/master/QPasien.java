package id.edmaputra.uwati.entity.master;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPasien is a Querydsl query type for Pasien
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPasien extends EntityPathBase<Pasien> {

    private static final long serialVersionUID = 21381153L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPasien pasien = new QPasien("pasien");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<Integer> agama = createNumber("agama", Integer.class);

    public final StringPath alamat = createString("alamat");

    public final StringPath ibu = createString("ibu");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath identitas = createString("identitas");

    //inherited
    public final StringPath info = _super.info;

    public final StringPath jaminanKesehatan = createString("jaminanKesehatan");

    public final NumberPath<Integer> jenisKelamin = createNumber("jenisKelamin", Integer.class);

    public final StringPath kontak = createString("kontak");

    public final StringPath nama = createString("nama");

    public final StringPath nomorJaminan = createString("nomorJaminan");

    public final StringPath pekerjaan = createString("pekerjaan");

    public final QPelanggan pelanggan;

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPasien(String variable) {
        this(Pasien.class, forVariable(variable), INITS);
    }

    public QPasien(Path<? extends Pasien> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPasien(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPasien(PathMetadata<?> metadata, PathInits inits) {
        this(Pasien.class, metadata, inits);
    }

    public QPasien(Class<? extends Pasien> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pelanggan = inits.isInitialized("pelanggan") ? new QPelanggan(forProperty("pelanggan")) : null;
    }

}

