import React from 'react';
import { Button, ScrollView, Dimensions, StyleSheet, Text, TextInput, View } from 'react-native';

// RNSecureStorage
import RNSecureStorage, { ACCESSIBLE } from 'rn-secure-storage'

export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.set = this.set.bind(this);
    this.get = this.get.bind(this);
    this.remove = this.remove.bind(this);

    // State
    this.state = {
      userInput: null,
      value: null,
      isStored: undefined,
    }
  }

  set() {
    if (this.state.userInput !== null) {
      RNSecureStorage.set("key", this.state.userInput, { accessible: ACCESSIBLE.WHEN_UNLOCKED })
          .then((res) => {
            this.setState({
              isStored: true
            })
          }, (err) => {
            alert(err);
          });
    } else {
      alert("Please enter a value")
    }
  }

  get() {
    RNSecureStorage.get("key").then((val) => {
      this.setState({
        value: val
      })
    }).catch((err) => {
      alert(err)
    })
  }

  remove() {
    RNSecureStorage.remove("key").then((val) => {
      this.setState({
        value: null,
        isStored: false
      })
    }).catch((err) => {
      alert(err)
    })
  }

  render() {
    return (
        <ScrollView
            bounces={false}
            keyboardShouldPersistTaps='handled'
        >
          <View style={styles.container}>
            <View>
              <View>
                <Text style={{
                  fontSize: 12,
                  paddingBottom: 10,
                }}>Enter a value</Text>
                <TextInput style={styles.valueInput} placeholder={'Enter a value for storing'}
                           onChangeText={(text) => this.setState({ userInput: text })}/>
              </View>
              <View style={styles.buttonSet}>
                <Button title={"SET"} onPress={this.set}/>
                <Button title={"GET"} onPress={this.get}/>
                <Button title={"REMOVE"} onPress={this.remove}/>
              </View>
              <View style={styles.showcase}>
                <View style={{
                  flexDirection: 'row',
                  paddingBottom: 20
                }}><Text style={{
                  paddingRight: 20,
                }}>Value is stored:</Text><Text style={{
                  color: this.state.isStored ? '#478e47' : '#b43535'
                }}>{this.state.isStored ? "true" : "false"}</Text></View>
                <View style={styles.valueArea}>
                  <View style={styles.valueTitle}><Text style={{
                    alignSelf: 'center',
                    fontWeight: 'bold',
                    textAlign: 'center',
                  }}>VALUE</Text></View>
                  <Text style={styles.value}>{this.state.value}</Text>
                </View>
              </View>
            </View>
          </View>
        </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    height: Dimensions.get('window').height

  },
  valueInput: {
    padding: 10,
    backgroundColor: '#FFF',
    borderWidth: 1,
    borderColor: '#F1f1f1'
  },
  buttonSet: {
    flexDirection: 'row',
    paddingVertical: 25,
    justifyContent: 'space-between'
  },
  showcase: {
    paddingVertical: 10
  },
  valueArea: {
    paddingVertical: 10,
    flexDirection: 'column'
  },
  valueTitle: {
    borderBottomWidth: 1,
    paddingBottom: 5,
    marginBottom: 5,
    borderBottomColor: '#333'
  },
  value: {
    paddingTop: 25,
    fontWeight: 'bold',
    textAlign: 'center',
    fontSize: 16
  }
});
