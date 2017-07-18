# ContactFuzzySearch
Android通讯录模糊搜索 ,模糊搜索通讯录联系人。 排序按照A-Z顺序进行排序。基于pinyin4j。 使用请导入对应jar包;

![github](http://imya.gzdn.openstorage.cn/qiqu/1.png?token=7df5397ef0fae272e5305810a4b963f8aa54ea1b&e=11500361588 "github") 
如何使用
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

