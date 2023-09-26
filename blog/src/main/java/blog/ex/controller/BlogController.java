package blog.ex.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.ex.model.entity.BlogEntity;
import blog.ex.model.entity.UserEntity;
import blog.ex.service.BlogService;
import blog.ex.service.UserService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user/blog")

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	//ログイン画面処理
	@GetMapping("/list")
	public String getBlogListPage(Model model) {
		//ユーザーがログイン後、ユーザー情報、ログイン状態をを保存し、後でアクセスできるようにデータ型を（UserEntity）にします。
		UserEntity userList = (UserEntity) session.getAttribute("user");
		//userListがnullじゃなかったらuserがログインしている
		if(userList != null) {
			//UserEntityからユーザー名を取得
			String userName = userList.getUserName();
			//blogServiceのfindAllBlogPostメソッドを呼び出し、nullにしたらユーザー関係なく全部のブログを取得
			List<BlogEntity> blogList = blogService.findAllBlogPost(null);
			//model.addAttributeメソッドを使用して、HTMLにタイムリーフで挿入できる
			model.addAttribute("userName", userName);
			model.addAttribute("blogList", blogList);
			//ログイン成功したら、blog_list.htmlを表示する
			return "blog_list.html";
		}else {
			//ログインできなかった場合はログイン画面のまま。
			return "redirect:/user/login";
		}	
	}
	
	//ブログ画面処理　{blogId}はクリックしたブログを特定してます
	@GetMapping("/content/{blogId}")
	public String getBlogContentPage(@PathVariable Long blogId, Model model) {
		//今ログインしてるユーザーを示す
		UserEntity userList = (UserEntity) session.getAttribute("user");
		String userName = userList.getUserName();
		model.addAttribute("userName", userName);
		
		//blogIdで特定のブログ投稿を取得します
		BlogEntity blog = blogService.getBlogPost(blogId);
		
		if(blog == null) {
			//ブログ見つからない場合はlist画面に戻ります
			return "redirect:/user/blog/list";
		}else {
			model.addAttribute("blog", blog);
			//ブログ投稿の作成者を取得します。
			UserEntity aythor = userService.getUserById(blog.getUserId());
			model.addAttribute("authorName", aythor.getUserName());
			return "blog_content.html";
		}
	}
	
	//検索機能
	@GetMapping("/search")
	//searchにsearchTermを請求する
	public String searchBlogs(@RequestParam(name = "searchTerm") String searchTerm, Model model) {
		UserEntity userList = (UserEntity) session.getAttribute("user");
		if(userList != null) {
			//ユーザーリストからIdとNameを取得
			Long userId = userList.getUserId();
			String userName = userList.getUserName();
			//"blogService.searchBlogs(userId, searchTerm)" メソッドを呼び出して、ログインしているユーザーのコンテキスト内で、指定された検索用語に一致するブログを検索します。
			List<BlogEntity> searchResults = blogService.searchBlogs(userId,searchTerm);
			model.addAttribute("blogList",searchResults);
			model.addAttribute("userName",userName);
			return "redirect:/user/blog/list";
			
		}else {
			return "redirect:/user/login";
		}
		
		
	}
	
	@GetMapping("/register")
	public String getBlogRegisterPage(Model model, HttpSession session) {
		UserEntity userList = (UserEntity) session.getAttribute("user");
		if(userList == null) {
			return "redirect:/user/login";
		}
		
		String userName = userList.getUserName();
		model.addAttribute("userName",userName);
		return "blog_edit.html";
	}
	
	@PostMapping("/register/process")
	public String blogRegister(@RequestParam String blogTitle,
			
			@RequestParam Long categoryId,
			@RequestParam MultipartFile blogImage,
			@RequestParam String blogDetail, Model model) {
		UserEntity userList = (UserEntity) session.getAttribute("user");
		Long userId = userList.getUserId();
		System.out.println(categoryId);
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + blogImage.getOriginalFilename();
		try {
			Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog_img/" + fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
		LocalDate registerDate = LocalDate.now();
		if(blogService.createBlogPost(blogTitle, registerDate, fileName, blogDetail, categoryId, userId)) {
			return "blog_edit_flx.html";
		}else {
			return "blog_edit.html";
		}
		
	}
	
	@GetMapping("/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
	    UserEntity userList = (UserEntity) session.getAttribute("user");
	    String userName = userList.getUserName();
	    model.addAttribute("userName", userName);

	    BlogEntity blogList = blogService.getBlogPost(blogId);

	    if (blogList == null) {
	        return "redirect:/user/blog/list";
	    } else {
	        model.addAttribute("blogList", blogList);
	        return "blog_edit.html";
	    }
	}

	
	@PostMapping("/update")
	public String blogUpdate(@RequestParam String blogTitle,
	        @RequestParam Long categoryId,
	        @RequestParam String blogDetail,
	        @RequestParam Long blogId,
	        @RequestParam("newBlogImage") MultipartFile newBlogImage, Model model) {
	    UserEntity userList = (UserEntity) session.getAttribute("user");
	    Long userId = userList.getUserId();

	    if (blogService.editBlogPost(blogTitle, blogDetail, categoryId, userId, blogId)) {
	        if (!newBlogImage.isEmpty()) {
	            
	            String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + newBlogImage.getOriginalFilename();
	            try {
	                Files.copy(newBlogImage.getInputStream(), Path.of("src/main/resources/static/blog_img/" + fileName));
	                
	                blogService.editBlogImage(blogId, fileName, userId);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return "blog_edit_flx.html";
	    } else {
	        return "blog_delete.html";
	    }
	}

	
	
	
	
	//削除ページを取得
	@GetMapping("/delete/list")
	public String getBlogDeleteListPage(Long blogId, Model model) {
		UserEntity userList = (UserEntity) session.getAttribute("user");
		Long userId = userList.getUserId();
		
		String userName = userList.getUserName();
		
		List<BlogEntity>blogList = blogService.findAllBlogPost(userId);
		BlogEntity blog = blogService.getBlogPost(blogId);
		
		model.addAttribute("blog", blog);
		model.addAttribute("userName", userName);
		model.addAttribute("blogList", blogList);
		return "blog_delete.html";
	}
	
	
	//削除機能実装
	@PostMapping("/delete")
	public String blogDelete(@RequestParam Long blogId) {
		if(blogService.deleteBlog(blogId)) {
			return "blog_delete_flx";
		}else {
			return "blog_delete.html";
		}
	}
}
	