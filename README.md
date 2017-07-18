# ContactFuzzySearch
Android通讯录模糊搜索 ,模糊搜索通讯录联系人。 排序按照A-Z顺序进行排序。基于pinyin4j。 使用请导入对应jar包;

![github](http://imya.gzdn.openstorage.cn/qiqu/jdfw.gif?token=3517969efe35f3a3e08b4677c4bde78b7c5e3a09&e=11500363141 "github") 

<br>如何使用
===
1.导入jar包;

    compile project(path: ':mylibrary')
    
#### 使用方式：
1.获取通讯录联系人.排序已基于A-Z排序;
  
    new Thread(new Runnable() {
            @Override
            public void run() {
                SystemContact contacts = SystemContactsHelper.getInstance().getContacts(MainActivity.this);
                if (contacts.getError_code()==0){
                    List<SystemContact.Contact> contacts1 = contacts.getContacts();
                    setAdapter(contacts1);
                }else {
                    //error // or no permission
                    Log.i("MainActivity","error");
                }
            }
        }).start();
2.监控editview.模糊搜索;
  
     FuzzySearchHelper.getInstance().init(editText,MainActivity.this)
                .setOnFuzzySearchCallBack(new FuzzySearchHelper.FuzzySearchCallBack() {
                    @Override
                    public void onFuzzySearch(List<SystemContact.Contact> mateContactList) {
                        mateListView.setAdapter(new ContactAdapter(MainActivity.this,mateContactList));
                    }
                });

3.某些展示情况下需要用到首字母标示; 可以这么获得;

####
     /**
     * 获取首字母
     * @param contacts1
     */
    private void getFirstLetter(List<SystemContact.Contact> contacts1){
        String letter = contacts1.get(0).getLetter(); //
    }
4.获取缩写
####
     /**
     * 获取缩写  中文林俊杰 = ljj  不支持多音字
     * @param contacts1
     */
    private void getAbbreviate(List<SystemContact.Contact> contacts1){
        String abbreviate = contacts1.get(0).getAbbreviate();
    }
5.获取全拼
####
    /**
    * 获取全拼 林俊杰 =linjunjie //  不支持多音字
    * @param contacts1
    */
    private void getQuanPin(List<SystemContact.Contact> contacts1){
        String quanPin = contacts1.get(0).getQuanPin();
    }
