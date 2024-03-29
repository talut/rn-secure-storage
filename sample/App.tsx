/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from "react";
import { Animated, Button, SafeAreaView, StyleSheet, Text, View } from "react-native";
import RNSecureStorage, { ACCESSIBLE } from "rn-secure-storage";
import ScrollView = Animated.ScrollView;


const options = {
  accessible: ACCESSIBLE.WHEN_UNLOCKED
};

function App(): React.JSX.Element {


  const [message, setMessage] = React.useState<any>(null);
  const [error, setError] = React.useState<any>(null);
  const setItem = () => {
    RNSecureStorage.setItem("idToken", "sdoi34y5o34webfld,v sv", {
      accessible: ACCESSIBLE.WHEN_UNLOCKED
    }).then(res => {
      setMessage(res);
      setError(null);
    }).catch(err => {
        setError(err);
        setMessage(null);
      }
    );
  };

  const getItem = () => {
    RNSecureStorage.getItem("idToken").then(res => {
      setMessage(res);
      setError(null);
    }).catch(err => {
        setError(err);
        setMessage(null);
      }
    );
  };

  const removeItem = () => {
    RNSecureStorage.removeItem("idToken").then(res => {
      setMessage(res);
      setError(null);
    }).catch(err => {
        setError(err);
        setMessage(null);
      }
    );
  };

  const getAllKeys = () => {
    RNSecureStorage.getAllKeys().then(res => {
      if (res) setMessage(res.join(", "));
      setError(null);
    }).catch(err => {
        setError(err);
        setMessage(null);
      }
    );
  };

  const exist = () => {
    RNSecureStorage.exist("idToken").then(res => {
      setMessage(res ? "Exists" : "Not exists");
      setError(null);
    }).catch(err => {
        setError(err);
        setMessage(null);
      }
    );
  };

  const clear = () => {
    RNSecureStorage.clear().then(res => {
      setMessage(res);
      setError(null);
    }).catch(err => {
        setError(err);
        setMessage(null);
      }
    );
  };


  const multiSet = () => {
    const items = {
      "multikey1": "multikey1 value",
      "multikey2": "multiksdfklhds,v xo4yrotrhukjsbngey2 value"
    };


    RNSecureStorage.multiSet(items, {
      accessible: ACCESSIBLE.WHEN_UNLOCKED
    }).then(res => {
      if (res) setMessage(JSON.stringify(res));
      setError(null);
    }).catch(err => {
      setError(err);
      setMessage(null);
    });
  };

  const multiGet = () => {
    RNSecureStorage.multiGet(["multikey1", "multikey2", "multikey3"]).then(res => {
      if (res) setMessage(JSON.stringify(res));
      setError(null);
    }).catch(err => {
        setError(err);
        setMessage(null);
      }
    );
  };

  const multiRemove = () => {
    RNSecureStorage.multiRemove(["multikey1", "multikey3"]).then(res => {
        console.log(res);
        setMessage(res);
        setError(null);
      }
    ).catch(err => {
      console.log("err", err);
      setError(err);
      setMessage(null);
    });
  };


  return (
    <SafeAreaView style={styles.container}>
      <ScrollView>
        <View style={styles.content}>
          <View style={styles.inputArea}>
            <Button onPress={setItem} title="Set Item" />
          </View>
          <View style={styles.inputArea}>
            <Button onPress={getItem} title="Get key value" />
          </View>

          <View style={styles.inputArea}>
            <Button onPress={getAllKeys} title="Get all stored keys" />
          </View>

          <View style={styles.inputArea}>
            <Button onPress={removeItem} title="Remove key value" />
          </View>

          <View style={styles.inputArea}>
            <Button onPress={exist} title="Check if key exists" />
          </View>

          <View style={styles.inputArea}>
            <Button onPress={clear} title="Clear all stored items" />
          </View>

          <View style={styles.inputArea}>
            <Button onPress={multiSet} title="Multiple key/value set" />
          </View>
          <View style={styles.inputArea}>
            <Button onPress={multiGet} title="Multiple key/value get" />
          </View>

          <View style={styles.inputArea}>
            <Button onPress={multiRemove} title="Multiple key/value remove" />
          </View>

          {message && <View style={[styles.inputArea, {
            width: "100%"
          }]}>
            <Text style={styles.value}>Message: {message}</Text>
          </View>}

          {error && <View style={[styles.inputArea, {
            width: "100%"
          }]}>
            <Text style={styles.value}>Error: <Text style={styles.err}>{JSON.stringify(error)}</Text></Text>
          </View>}

        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: "#e1e1e1",
    flex: 1
  },
  content: {
    margin: 16,
    flex: 1,
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "space-between"

  },
  inputArea: {
    width: "48%",
    backgroundColor: "#efefef",
    padding: 16,
    borderRadius: 6,
    shadowColor: "#333",
    shadowOffset: {
      width: 2,
      height: 2
    },
    shadowOpacity: .1,
    shadowRadius: 4,
    elevation: 5,
    marginBottom: 16
  },
  input: {
    borderWidth: 1,
    borderColor: "black",
    height: 40,
    margin: 12,
    padding: 10,
    borderRadius: 6
  },
  value: {
    fontWeight: "bold",
    fontSize: 16,
    margin: 12
  },
  err: {
    fontWeight: "bold",
    fontSize: 16,
    margin: 12,
    color: "red"
  }
});

export default App;
