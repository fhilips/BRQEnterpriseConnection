create table candidato (id  bigserial not null, cpf varchar(255), data_nascimento date, email varchar(255), genero varchar(255), nome varchar(255), telefone varchar(255), primary key (id));
create table candidato_certificado (id  bigserial not null, candidato_id int8, certificado_id int8, skill_id int8, primary key (id));
create table candidato_skill (id  bigserial not null, candidato_id int8, skill_id int8, primary key (id));
create table certificado (id  bigserial not null, codigo varchar(255), nome varchar(255), primary key (id));
create table skill (id  bigserial not null, nome varchar(255), primary key (id));
alter table certificado add constraint UK_r8lvtp5kya87ua8ps01t66uyu unique (codigo);
alter table candidato_certificado add constraint FKqkm46udwm50na5t47nxgiuaok foreign key (candidato_id) references candidato;
alter table candidato_certificado add constraint FKhrt03phh7pfrnuvr8am5v0avu foreign key (certificado_id) references certificado;
alter table candidato_certificado add constraint FKsf3for8t16h0h6qvc4ekm4a1a foreign key (skill_id) references skill;
alter table candidato_skill add constraint FKkcngm7v6px45r5m7wjo5g0m0k foreign key (candidato_id) references candidato;
alter table candidato_skill add constraint FKmf5cyxpubwt95tceudf3ni61f foreign key (skill_id) references skill;
