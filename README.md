# ViewStateLayout
layout view that can handle several state like loading, empty state and error state

```
implementation 'com.github.FaytMaruf:ViewStateLayout:1.0.2'
```
### Usage
in your xml
```
<com.irtikaz.view.widget.ViewStateLayout
        android:id="@+id/viewstate_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:theme="@style/yourstyle">

        ...
    </com.irtikaz.view.widget.ViewStateLayout>
```
in your activity

```
//to show loading view
viewstate_layout.showLoading()  

//to show main content of your activity
viewstate_layout.showContent()  

//to show error without button
val img = getDrawable(R.drawable.ic_eform_riwayat)
layout_viewstate.showErrorWithImage(img!!,
                getString(R.string.error_title),
                getString(R.string.error_message),
                null, null)
```
