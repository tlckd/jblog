select blog.blog_id as blogId, blog.title as blogTitle, blog.logo as blogLogo,
category.no as categoryNo, category.name as categoryName, category.description as categoryDescription,
post.no as postNo, post.title as postTitle, post.contents as postContents
from blog, category, post 
where blog.blog_id = category.blog_id and category.no = post.category_no;

select count(*) from post;
select * from post;
select * from category
where blog_id='aa'
order by no desc
limit 1 ;

select blog.blog_id as blogId, blog.title as blogTitle, blog.logo as blogLogo,
category.no as categoryNo, category.name as categoryName, category.description as categoryDescription
from blog, category 
where blog.blog_id = category.blog_id and blog.blog_id='aa';


select blog_id as blogId, title, logo
 from blog where blog_id='aa';

select no, name, description, blog_id as blogId
from category
where blog_id='aa'
order by no;

select * from category;
select * from user;

select no,title,contents,category_no as categoryNo
from post
where category_no=2 and no=7;

select no,title,contents,category_no as categoryNo
from post
where category_no=2
limit 1;

select no,title,contents,category_no as categoryNo
from post
order by no desc
limit 1;


insert into post values(null,"테스트용타이틀4","테스트용컨텐츠4",2);

select no,title,contents,category_no as categoryNo
from post
order by no desc;

select count(*)
from category a ,post b
where blog_id='aa' and a.no = b.category_no;

select no, name, description, blog_id as blogId, (select count(*)
													from category a ,post b
													where blog_id='aa' and a.no = b.category_no and a.no='2'
															) as postCount
from category
where blog_id='aa'
order by no;


select *
  from category a;

select count(*) as postCount
  from post a, category b
 where a.category_no = b.no
group by b.no;

select no, name, description, blog_id as blogId, (select count(*)
													from post a, category b
													where a.category_no = b.no
													group by b.no) as postCount
from category
where blog_id= 'aa'
order by no;



insert into post values(null,"테스트용타이틀4","테스트용컨텐츠4",2);