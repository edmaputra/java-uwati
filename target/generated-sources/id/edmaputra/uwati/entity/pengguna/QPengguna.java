package id.edmaputra.uwati.entity.pengguna;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPengguna is a Querydsl query type for Pengguna
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPengguna extends EntityPathBase<Pengguna> {

    private static final long serialVersionUID = -1728209741L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPengguna pengguna = new QPengguna("pengguna");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<Integer> countKesalahan = createNumber("countKesalahan", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final StringPath info = _super.info;

    public final BooleanPath isAktif = createBoolean("isAktif");

    public final BooleanPath isPertamaKali = createBoolean("isPertamaKali");

    public final id.edmaputra.uwati.entity.master.QKaryawan karyawan;

    public final StringPath kataSandi = createString("kataSandi");

    public final StringPath nama = createString("nama");

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPengguna(String variable) {
        this(Pengguna.class, forVariable(variable), INITS);
    }

    public QPengguna(Path<? extends Pengguna> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPengguna(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPengguna(PathMetadata<?> metadata, PathInits inits) {
        this(Pengguna.class, metadata, inits);
    }

    public QPengguna(Class<? extends Pengguna> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.karyawan = inits.isInitialized("karyawan") ? new id.edmaputra.uwati.entity.master.QKaryawan(forProperty("karyawan"), inits.get("karyawan")) : null;
    }

}

