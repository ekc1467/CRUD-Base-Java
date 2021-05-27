import java.util.ArrayList;
import java.util.Scanner;

class Post {
    private int number;
    private String name;
    private String title;
    Scanner sc = new Scanner(System.in);

    public void setNumber(int number) {
        this.number = number;
    }

    public Boolean InputPost(PostManager PM) {
        System.out.print("Title : ");
        String titleCheck = sc.nextLine();
        //그냥 title에 바로 받으면 변경할 때 바로 업데이트 되는 바람에 중복된다고 계속된다.
        if (PM.FindPost(titleCheck) != null) {
            return false;
        }
        title = titleCheck;
        ModifyPost();
        return true;
    }

    public void ModifyPost() {
        System.out.print("Name : ");
        name = sc.nextLine();
    }


    public Boolean CompareName(String title) {
        return title.equals(this.title);
    }

    public void PrintData() {
        System.out.println("----------------------------------");
        System.out.println("게시글 번호: " + number);
        System.out.println("게시글 제목: " + title);
        System.out.println("게시글 이름: " + name);
    }
}

class PostManager {
    public void CreatePost() {
        Post p = new Post();
        if (p.InputPost(this)) {
            PostList.add(p);
            number++;
            p.setNumber(number);
        } else {
            System.out.println("이미 등록된 제목입니다.");
        }
    }

    public Post FindPost(String title) {
        int Index = FindPostIndex(title);
        if (Index != -1) {
            return PostList.get(Index);
        }
        return null;
    }

    public int FindPostIndex(String title) {
        for (int i = 0; i < PostList.size(); i++) {
            Post p = PostList.get(i);
            if (p.CompareName(title)) {
                return i;
            }
        }
        return -1;
    }

    public void PrintPost() {
        for (Post p : PostList) {
            p.PrintData();
        }
        System.out.println("----------------------------------");
    }

    public void UpdatePost() {
        System.out.println("업데이트 하려는 게시글 제목: ");
        String title = sc.nextLine();
        Post p = FindPost(title);
        if (p != null) {
            if (p.InputPost(this)) {
                System.out.println("완료되었습니다.");
            } else {
                System.out.println("이미 등록된 제목입니다.");
            }
        } else {
            System.out.println("찾으시는 게실글이 없습니다.");
        }
    }

    public void DeletePost() {
        System.out.println("삭제 하려는 게시글 제목: ");
        String title = sc.nextLine();
        int index = FindPostIndex(title);
        if (index != -1) {
            PostList.remove(index);
            System.out.println("삭제 완료 되었습니다.");
        } else {
            System.out.println("삭제하려는 데이터는 없습니다");
        }

    }

    public void Menu() {
        int iChoice = 1;
        try {
            while (iChoice != 0) {
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                Scanner sc = new Scanner(System.in);
                iChoice = sc.nextInt();
                switch (iChoice) {
                    case 1: {
                        CreatePost();
                    }
                    break;
                    case 2: {
                        PrintPost();
                    }
                    break;
                    case 3: {
                        UpdatePost();
                    }
                    break;
                    case 4: {
                        DeletePost();
                    }
                    break;
                    default:
                        throw new Exception();
                }
            }
        } catch (Exception e) {
            System.out.println("종료");
        }
    }

    ArrayList<Post> PostList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    static int number = 0;
}

class Main {
    public static void main(String[] Args) {
        PostManager PM = new PostManager();
        PM.Menu();
        System.exit(0);
    }
}
