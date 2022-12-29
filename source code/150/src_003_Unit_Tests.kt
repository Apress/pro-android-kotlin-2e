package book.andrkotlpro.mocktest

import android.database.sqlite.SQLiteDatabase
import androidx.activity.ComponentActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.
    PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox
import java.lang.reflect.Constructor
import java.lang.reflect.Method


@RunWith(PowerMockRunner::class)
@PrepareForTest(MainActivity::class)
class MainActivityTest {

    @Test
    fun table_created() {
        val componentActivityConstructors: 
                Array<Constructor<*>> = 
        PowerMockito.constructors(
            ComponentActivity::class.java.getConstructor()
        )
        PowerMockito.suppress(
            componentActivityConstructors)

        val activity = MainActivity()
        val activitySpy = spy(activity)
        val db = mock(SQLiteDatabase::class.java)

        // given
        given(activitySpy.openOrCreateDatabase(
            anyString(), anyInt(), any())).willReturn(db)

        // when
        Whitebox.invokeMethod<Unit>(
            activitySpy,"saveInDb","hello")

        // then
        verify(db).execSQL(ArgumentMatchers.argThat {arg ->
            arg.toString().matches(
                Regex("(?i)create table.*\\bMyItems\\b.*")
            )
        })

    }

    @Test
    fun item_inserted() {
        val componentActivityConstructors: 
                Array<Constructor<*>> = 
        PowerMockito.constructors(
            ComponentActivity::class.java.getConstructor()
        )
        PowerMockito.suppress(
            componentActivityConstructors)

        val activity = MainActivity()
        val activitySpy = spy(activity)
        val db = mock(SQLiteDatabase::class.java)

        // given
        given(activitySpy.openOrCreateDatabase(
            anyString(), anyInt(), any())).willReturn(db)

        // when
        Whitebox.invokeMethod<Unit>(
            activitySpy,"saveInDb","hello")

        // then
        verify(db).execSQL(ArgumentMatchers.argThat {arg ->
            arg.toString().matches(
                Regex("(?i)insert into MyItems\\b.*")
            )
        }, ArgumentMatchers.argThat { arg ->
            val arr = arg as Array<Any>
            arr[0] == "hello" &&
                    arr[1] is Number
        })
    }
}
