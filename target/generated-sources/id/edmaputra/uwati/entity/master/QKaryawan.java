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

    public static final QKaryawan karyawan = new QKaryawan("karyawan");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final StringPath alamat = createString("alamat");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath jabatan = createString("jabatan");

    public final StringPath nama = createString("nama");

    public final ListPath<id.edmaputra.uwati.entity.pengguna.Pengguna, id.edmaputra.uwati.entity.pengguna.QPengguna> pengguna = this.<id.edmaputra.uwati.entity.pengguna.Pengguna, id.edmaputra.uwati.entity.pengguna.QPengguna>createList("pengguna", id.edmaputra.uwati.entity.pengguna.Pengguna.class, id.edmaputra.uwati.entity.pengguna.QPengguna.class, PathInits.DIRECT2);

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
        super(Karyawan.class, forVariable(variable));
    }

    public QKaryawan(Path<? extends Karyawan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKaryawan(PathMetadata<?> metadata) {
        super(Karyawan.class, metadata);
    }

}

