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

    public static final QPengguna pengguna = new QPengguna("pengguna");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<Integer> countKesalahan = createNumber("countKesalahan", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isAktif = createBoolean("isAktif");

    public final BooleanPath isPertamaKali = createBoolean("isPertamaKali");

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
        super(Pengguna.class, forVariable(variable));
    }

    public QPengguna(Path<? extends Pengguna> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPengguna(PathMetadata<?> metadata) {
        super(Pengguna.class, metadata);
    }

}

