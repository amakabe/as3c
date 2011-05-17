
as3c
====


なにこれ
--------

Flexコンパイラのラッパです。

FlexSDKに含まれる`flex-compiler-oem.jar`を使用して
コンパイル対象ファイル(と依存ファイル)の変更を監視し
自動でコンパイルします。



依存
----

as3cをビルド/実行するためには Java, Ant, Flex SDKが必要です。

以下から最新のFlex SDKをダウンロードすることができます。

[Flex SDK](http://opensource.adobe.com/wiki/display/flexsdk/Flex+SDK)


使い方
------

### ビルド

ソースをダウンロードします。

    $ git clone git://github.com/amakabe/as3c

`build.properties`を編集し依存ライブラリとインストール先のパスを設定します。

    $ cd as3c
    $ vi build.properties

ビルド/インストールします。

    $ ant

インストール先のディレクトリに以下のレイアウトでインストールされます。

    bin/3c       ... ラッパースクリプト
    lib/as3c.jar ... 本体


### 実行

ラッパースクリプトから以下のように実行します。

    $ ./dist/bin/3c コンパイル対象ファイル


#### オプション

以下のオプションを受け付けます。

`-o PATH`
: 出力される SWFファイルのパスを指定します。

`-l PATH`
: 指定ファイルの変更を監視し、末尾を継続的に標準出力に出力します。
  フラッシュプレーヤのログファイル`flashlog.txt`のパスを指定することで
  コンパイラの出力と`flashlog.txt`への追記をマージして
  端末に表示することができます。



作者と連絡先
------------

amakabeが書きました。

不具合や問題ありましたら以下までご一報ください。

[github/amakabe](http://github.com/amakabe)


