var Gritter = function() {
	$('#gritter-tambah-sukses').click(function() {
		$.gritter.add({
			title : 'SUKSES',
			text : 'Data Berhasil Ditambahkan'
		});
		return false;
	});

	$('#gritter-tambah-gagal').click(function() {
		$.gritter.add({
			title : 'GAGAL',
			text : 'Data Gagal Ditambahkan'
		});
		return false;
	});

	$('#gritter-edit-sukses').click(function() {
		$.gritter.add({
			title : 'SUKSES',
			text : 'Data Berhasil Diubah'
		});
		return false;
	});

	$('#gritter-edit-gagal').click(function() {
		$.gritter.add({
			title : 'GAGAL',
			text : 'Data Gagal Diubah'
		});
		return false;
	});

	$('#gritter-hapus-sukses').click(function() {
		$.gritter.add({
			title : 'SUKSES',
			text : 'Hapus Data Sukses'
		});
		return false;
	});

	$('#gritter-hapus-gagal').click(function() {
		$.gritter.add({
			title : 'GAGAL',
			text : 'Hapus Data Gagal'
		});
		return false;
	});
	
}();