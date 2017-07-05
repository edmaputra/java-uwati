package id.edmaputra.uwati.entity.master;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QKaryawan is a Querydsl query type for Karyawan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QKaryawan extends EntityPathBase<Karyawan> {

    private static final long serialVersionUID = -1065625743L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKaryawan karyawan = new QKaryawan("karyawan");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final StringPath alamat = createString("alamat");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath jabatan = createString("jabatan");

    public final StringPath nama = createString("nama");

    public final id.edmaputra.uwati.entity.pengguna.QPengguna pengguna;

    public final StringPath sip = createString("sip");

    public final StringPath spesialis = createString("spesialis");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QKaryawan(String variable) {
        this(Karyawan.class, forVariable(variable), INITS);
    }

    public QKaryawan(Path<? extends Karyawan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKaryawan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKaryawan(PathMetadata<?> metadata, PathInits inits) {
        this(Karyawan.class, metadata, inits);
    }

    public QKaryawan(Class<? extends Karyawan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pengguna = inits.isInitialized("pengguna") ? new id.edmaputra.uwati.entity.pengguna.QPengguna(forProperty("pengguna"), inits.get("pengguna")) : null;
    }

}

