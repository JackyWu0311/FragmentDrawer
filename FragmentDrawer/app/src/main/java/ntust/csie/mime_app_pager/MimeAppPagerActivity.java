package ntust.csie.mime_app_pager;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MimeAppPagerActivity extends AppCompatActivity {

    private DrawerLayout dlDrawer;
    private ListView lvDrawerItems;
    private Toolbar toolbar;
    private FragmentDrawerAdapter mDrawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mime_app_pager);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        toolbar.setTitleTextAppearance(this, (int)getResources().getDimension(R.dimen.action_menu_item_text_size));

        dlDrawer = (DrawerLayout)findViewById(R.id.dlDrawer);
        mDrawerAdapter = new FragmentDrawerAdapter(this, dlDrawer , toolbar , R.string.drawer_open , R.string.drawer_close);
        mDrawerAdapter.syncState();
        dlDrawer.addDrawerListener(mDrawerAdapter);

        lvDrawerItems = (ListView)findViewById(R.id.lvDrawerItems);
        ArrayAdapter adapter = new ArrayAdapter(MimeAppPagerActivity.this , android.R.layout.simple_expandable_list_item_1, mDrawerAdapter.getPageTitles());
        lvDrawerItems.setAdapter(adapter);


        lvDrawerItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id) {
                mDrawerAdapter.setItemSelected(pos);
                toolbar.setTitle(mDrawerAdapter.getPageTitle(pos));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.flFrag , mDrawerAdapter.getFragmentAtPos(pos)).commit();
                dlDrawer.closeDrawer(lvDrawerItems);
            }
        });
        toolbar.setTitle(mDrawerAdapter.getPageTitle(0));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFrag , mDrawerAdapter.getFragmentAtPos(0))
                .commit();
    }






















}
