create table resume
(
    uuid      character(36) primary key not null,
    full_name text                      NOT NULL
);

create table contact
(
    id          SERIAL primary key,
    resume_uuid character(36) not null REFERENCES resume (uuid) ON DELETE CASCADE,
    type        text          not null,
    value       text          not null
);

CREATE UNIQUE INDEX contact_uuid_type_index ON contact (resume_uuid, type);

CREATE TABLE section (
    id SERIAL PRIMARY KEY ,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE ,
    type TEXT NOT NULL,
    content TEXT NOT NULL
);
CREATE UNIQUE INDEX section_idx ON section (resume_uuid, type);

