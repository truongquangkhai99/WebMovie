USE [master]
GO
/****** Object:  Database [asm_java_4]    Script Date: 2/4/2022 3:35:25 PM ******/
CREATE DATABASE [asm_java_4]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'asm_java_4', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\asm_java_4.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'asm_java_4_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\asm_java_4_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [asm_java_4] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [asm_java_4].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [asm_java_4] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [asm_java_4] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [asm_java_4] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [asm_java_4] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [asm_java_4] SET ARITHABORT OFF 
GO
ALTER DATABASE [asm_java_4] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [asm_java_4] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [asm_java_4] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [asm_java_4] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [asm_java_4] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [asm_java_4] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [asm_java_4] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [asm_java_4] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [asm_java_4] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [asm_java_4] SET  ENABLE_BROKER 
GO
ALTER DATABASE [asm_java_4] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [asm_java_4] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [asm_java_4] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [asm_java_4] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [asm_java_4] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [asm_java_4] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [asm_java_4] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [asm_java_4] SET RECOVERY FULL 
GO
ALTER DATABASE [asm_java_4] SET  MULTI_USER 
GO
ALTER DATABASE [asm_java_4] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [asm_java_4] SET DB_CHAINING OFF 
GO
ALTER DATABASE [asm_java_4] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [asm_java_4] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [asm_java_4] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [asm_java_4] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'asm_java_4', N'ON'
GO
ALTER DATABASE [asm_java_4] SET QUERY_STORE = OFF
GO
USE [asm_java_4]
GO
/****** Object:  Table [dbo].[favorites]    Script Date: 2/4/2022 3:35:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[favorites](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [varchar](55) NULL,
	[videoId] [varchar](55) NULL,
	[likeDate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[share]    Script Date: 2/4/2022 3:35:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[share](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [varchar](55) NULL,
	[videoId] [varchar](55) NULL,
	[email] [varchar](100) NULL,
	[shareDate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 2/4/2022 3:35:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [varchar](55) NOT NULL,
	[password] [varchar](55) NULL,
	[email] [varchar](100) NULL,
	[fullname] [nvarchar](100) NULL,
	[admin] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[videos]    Script Date: 2/4/2022 3:35:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[videos](
	[id] [varchar](55) NOT NULL,
	[title] [nvarchar](55) NULL,
	[poster] [varchar](100) NULL,
	[views] [int] NULL,
	[description] [nvarchar](100) NULL,
	[active] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[users] ([id], [password], [email], [fullname], [admin]) VALUES (N'1', N'123', N'tai@gmail.com', N'nguyen tai', 0)
GO
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'1', N'Kinh dị', N'video1.jpg', 0, N'https://www.youtube.com/embed/qxFy4yW4zrA', 0)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'10', N'Sự Thật', N'video10.jpg', 1, N'https://www.youtube.com/embed/lpyz4Ns1A-I', 0)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'11', N'Kinh dị', N'video11.jpg', 0, N'https://www.youtube.com/embed/pbDYKBMp2Qs', 1)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'2', N'Sự Thật', N'video2.jpg', 1, N'https://www.youtube.com/embed/3te6TZztQ00', 0)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'3', N'Sự thật', N'video3.jpg', 5, N'https://www.youtube.com/embed/pTxQw1Q9CWw', 0)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'4', N'Review sản phẩm', N'video4.jpg', 1, N'https://www.youtube.com/embed/uYMiSDeAiv4', 0)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'5', N'Review sản phẩm', N'video5.jpg', 6, N'https://www.youtube.com/embed/2h7lw8-7QnA', 1)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'6', N'Kinh dị', N'video6.jpg', 4, N'https://www.youtube.com/embed/lgXSzX0tX04', 0)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'7', N'Sự thật', N'video7.jpg', 1, N'https://www.youtube.com/embed/Ebqv9OAxVEI', 0)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'8', N'Kinh dị', N'video8.jpg', 9, N'https://www.youtube.com/embed/k4hVEqszZBs', 1)
INSERT [dbo].[videos] ([id], [title], [poster], [views], [description], [active]) VALUES (N'9', N'Nhạc', N'video9.jpg', 1, N'https://www.youtube.com/embed/w6eWno3iAjk', 1)
GO
ALTER TABLE [dbo].[favorites]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[favorites]  WITH CHECK ADD FOREIGN KEY([videoId])
REFERENCES [dbo].[videos] ([id])
GO
ALTER TABLE [dbo].[share]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[share]  WITH CHECK ADD FOREIGN KEY([videoId])
REFERENCES [dbo].[videos] ([id])
GO
USE [master]
GO
ALTER DATABASE [asm_java_4] SET  READ_WRITE 
GO
