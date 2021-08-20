alter table restaurante_usuario add constraint fk_rest_usuario_usuario
foreign key (usuario_id) references usuario (id);

alter table restaurante_usuario add constraint fk_rest_usuario_restaurante
foreign key (restaurante_id) references restaurante (id);