INSERT INTO public.users (id, age, email, first_name, last_name, password, username, phone, profile_picture, profile_thumbnail) VALUES (1, 28, 'admin@admin.com', 'admin', 'admin', 'Azer123&', 'admin_admin_0000',null, null, null);
INSERT INTO public.users (id, age, email, first_name, last_name, password, username, phone, profile_picture, profile_thumbnail) VALUES (2, 28, 'hosni@gmail.com', 'hosni', 'bounechada', 'Azer123&', 'hosni_bounechada_0000',null, null, null);
INSERT INTO public.users (id, age, email, first_name, last_name, password, username, phone, profile_picture, profile_thumbnail) VALUES (3, 28, 'mohammed@gmail.com', 'mohammed', 'bounab', 'Azer123&', 'mohammed_bounab_0000',null, null, null);

INSERT INTO public.roles (id, authority) VALUES (1, 'ADMIN');
INSERT INTO public.roles (id, authority) VALUES (2, 'USER');

INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO public.user_roles (user_id, role_id) VALUES (3, 2);

INSERT INTO public.posts (id, content, user_id) VALUES (1, 'First Admin post', 1);
INSERT INTO public.posts (id, content, user_id) VALUES (2, 'First Hosni post', 2);
INSERT INTO public.posts (id, content, user_id) VALUES (3, 'First Mohammed post', 3);
INSERT INTO public.posts (id, content, user_id) VALUES (4, 'Second Hosni post', 2);
