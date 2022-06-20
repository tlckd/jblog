select blog.blog_id as blogId, blog.title as blogTitle, blog.logo as blogLogo,
category.no as categoryNo, category.name as categoryName, category.description as categoryDescription,
post.no as postNo, post.title as postTitle, post.contents as postContents
from blog, category, post 
where blog.blog_id = category.blog_id and category.no = post.category_no;

select count(*) from post;

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
select * from post;
insert into post values(null,"테스트용타이틀","테스트용컨텐츠","aa");

